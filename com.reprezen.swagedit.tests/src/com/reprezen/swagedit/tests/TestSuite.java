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
package com.reprezen.swagedit.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.reprezen.swagedit.assist.JsonReferenceProposalProviderTest;
import com.reprezen.swagedit.editor.hyperlinks.DefinitionHyperlinkDetectorTest;
import com.reprezen.swagedit.editor.hyperlinks.JsonReferenceHyperlinkDetectorTest;
import com.reprezen.swagedit.editor.hyperlinks.PathParamHyperlinkDetectorTest;
import com.reprezen.swagedit.editor.outline.AbstractNodeTest;
import com.reprezen.swagedit.editor.outline.OutlineStyledLabelProviderTest;
import com.reprezen.swagedit.json.references.JsonReferenceFactoryTest;
import com.reprezen.swagedit.validation.ErrorProcessorTest;
import com.reprezen.swagedit.validation.MultipleSwaggerErrorMessageTest;
import com.reprezen.swagedit.validation.ReferenceValidatorTest;
import com.reprezen.swagedit.validation.ValidatorTest;

@RunWith(Suite.class)
@SuiteClasses({ SwaggerContentAssistProcessorTest.class,//
        SwaggerDocumentTest.class,//
        SwaggerProposalProviderTest.class,//
        SwaggerSchemaTest.class,//
        JsonReferenceHyperlinkDetectorTest.class,//
        JsonReferenceFactoryTest.class,//
        PathParamHyperlinkDetectorTest.class,//
        DefinitionHyperlinkDetectorTest.class,//
        ValidationMessageTest.class,//
        ValidatorTest.class,//
        CodeTemplateContextTest.class,//
        ErrorProcessorTest.class,//
        MultipleSwaggerErrorMessageTest.class,//
        ReferenceValidatorTest.class,//
        JsonReferenceProposalProviderTest.class,//
        AbstractNodeTest.class,//
        OutlineStyledLabelProviderTest.class })
public class TestSuite {
}
