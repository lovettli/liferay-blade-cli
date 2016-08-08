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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.liferay.blade.api.SearchResult;
import com.liferay.blade.eclipse.provider.XMLFileSAX;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Andy Wu
 */
@SuppressWarnings("restriction")
public class CategoryEntryforPagesTest {

	@Before
	public void setUp() {
		assertTrue(testFile.exists());
		component = new CategoryEntryforPages();
	}

	@Test
	public void tesCategoryEntryforPagesTest() throws Exception {
		List<SearchResult> problems = component.searchFile(testFile, new XMLFileSAX(testFile));

		assertNotNull(problems);
		assertEquals(1, problems.size());
	}

	private CategoryEntryforPages component;
	private final File testFile = new File(
		"projects/test-portlet/docroot/WEB-INF/liferay-portlet.xml");

}
