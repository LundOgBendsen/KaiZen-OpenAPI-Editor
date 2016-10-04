/*******************************************************************************
 * Copyright (c) 2016 ModelSolv, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    ModelSolv, Inc. - initial API and implementation and/or initial documentation
 *******************************************************************************/
package com.reprezen.swagedit.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import com.google.common.io.CharStreams;
import com.reprezen.swagedit.editor.SwaggerDocument;

public class DocumentUtils {

    public static FileEditorInput getActiveEditorInput() {
        IEditorInput input = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()
                .getEditorInput();

        return input instanceof FileEditorInput ? (FileEditorInput) input : null;
    }

    public static SwaggerDocument getDocument(IPath path) {
        if (path == null || !path.getFileExtension().matches("ya?ml")) {
            return null;
        }

        InputStream content = null;
        IFile file = getWorkspaceFile(path);
        if (file == null) {
            IFileStore store = getExternalFile(path);
            if (store != null) {
                try {
                    content = store.openInputStream(EFS.NONE, null);
                } catch (CoreException e) {
                    content = null;
                }
            }
        } else if (file.exists()) {
            try {
                content = file.getContents();
            } catch (CoreException e) {
                content = null;
            }
        }

        if (content == null) {
            return null;
        }

        SwaggerDocument doc = new SwaggerDocument();
        try {
            doc.set(CharStreams.toString(new InputStreamReader(content)));
        } catch (IOException e) {
            return null;
        }

        return doc;
    }

    /**
     * @param uri
     *            - URI, representing an absolute path
     * @return
     */
    public static IFile getWorkspaceFile(URI uri) {
        return getWorkspaceFile(new Path(uri.getPath()));
    }

    /**
     * @param path
     *            - absolute path to the element
     * @return
     */
    public static IFile getWorkspaceFile(IPath path) {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

        try {
            return root.getFileForLocation(path);
        } catch (Exception e) {
            return null;
        }
    }

    public static IFileStore getExternalFile(IPath path) {
        IFileStore fileStore = EFS.getLocalFileSystem().getStore(path);

        IFileInfo fileInfo = fileStore.fetchInfo();

        return fileInfo != null && fileInfo.exists() ? fileStore : null;
    }

}
