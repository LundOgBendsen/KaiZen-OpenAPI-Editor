package com.reprezen.swagedit.schema

import com.reprezen.swagedit.tests.utils.PointerHelpers
import org.junit.Test

import static org.hamcrest.core.IsCollectionContaining.*
import static org.junit.Assert.*

class SwaggerSchemaTest {

	extension PointerHelpers = new PointerHelpers

	val schema = new SwaggerSchema

	@Test
	def void testRootType() {
		assertNotNull(schema.rootType)

		val rootType = schema.rootType
		assertEquals(schema.asJson, rootType.content)
		assertTrue(rootType instanceof ObjectTypeDefinition)

		assertThat(
			(rootType as ObjectTypeDefinition).requiredProperties,
			hasItems("swagger", "info", "paths")
		)
	}

	@Test
	def void testSwaggerType() {
		val swaggerType = schema.rootType.getPropertyType("swagger")

		assertTrue(swaggerType instanceof TypeDefinition)
		assertEquals(schema.asJson.at('/properties/swagger'), swaggerType.content)
	}

	@Test
	def void testInfoType() {
		val infoType = schema.getType("/definitions/info".ptr)
		assertTrue(infoType instanceof ObjectTypeDefinition)

		assertThat(
			(infoType as ObjectTypeDefinition).requiredProperties,
			hasItems("version", "title")
		)

		val titleType = infoType.getPropertyType("title")
		assertTrue(titleType instanceof TypeDefinition)
		assertEquals(schema.getType("/definitions/info/properties/title".ptr), titleType)
	}

	@Test
	def void testPathType() {
		val pathType = schema.getType("/definitions/paths".ptr)

		assertTrue(pathType instanceof ObjectTypeDefinition)
	}

	@Test
	def void testParameterType() {
		val parametersType = schema.getType("/definitions/parametersList".ptr)

		assertTrue(parametersType instanceof ArrayTypeDefinition)
	}
}
