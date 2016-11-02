/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liferay.blade.upgrade.liferay70.apichanges;

import com.liferay.blade.api.FileMigrator;
import com.liferay.blade.api.SearchResult;
import com.liferay.blade.api.XMLFile;
import com.liferay.blade.upgrade.liferay70.XMLFileMigrator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.osgi.service.component.annotations.Component;

@Component(
	property = {
		"file.extensions=xml",
		"problem.title=copy-request-parameters init-param default value change",
		"problem.summary=The copy-request-parameters init parameter's default value is now set to true in all portlets that extend MVCPortlet.",
		"problem.tickets=LPS-54798",
		"problem.section=#changed-the-default-value-of-the-copy-request-parameters-init-parameter-for",
		"implName=MVCPortletInitParamsChangeXML"
	},
	service = FileMigrator.class
)
public class MVCPortletInitParamsChangeXML extends XMLFileMigrator {

	@Override
	protected List<SearchResult> searchFile(File file, XMLFile xmlFileChecker) {
		// check if it is portlet.xml file

		if (!"portlet.xml".equals(file.getName())) {
			return Collections.emptyList();
		}

		final List<SearchResult> results = new ArrayList<>();

		results.addAll(xmlFileChecker.findTag(
			"portlet-class", "com.liferay.util.bridges.mvc.MVCPortlet"));

		results.addAll(xmlFileChecker.findTag(
			"name", "copy-request-parameters"));

		return results;
	}

}
