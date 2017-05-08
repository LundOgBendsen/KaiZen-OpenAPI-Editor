package com.reprezen.swagedit.openapi3.assist;
/*******************************************************************************
 *  Copyright (c) 2016 ModelSolv, Inc. and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *  
 *  Contributors:
 *     ModelSolv, Inc. - initial API and implementation and/or initial documentation
 *******************************************************************************/
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.jface.text.templates.persistence.TemplateStore;

import com.reprezen.swagedit.core.assist.JsonContentAssistProcessor;
import com.reprezen.swagedit.core.assist.JsonProposalProvider;

public class OpenApi3ContentAssistProcessor extends JsonContentAssistProcessor {

	public OpenApi3ContentAssistProcessor(ContentAssistant ca) {
		super(ca, new JsonProposalProvider(), new OpenApi3ReferenceProposalProvider());
	}

	@Override
	protected TemplateStore getTemplateStore() {
		return null;
	}

	@Override
	protected ContextTypeRegistry getContextTypeRegistry() {
		return null;
	}

	@Override
	protected String getContextTypeId(String path) {
		return null;
	}

}