# NEW! Full OpenAPI 3.0 Editing Support
We are really pleased to inform you that KaiZen OpenAPI Editor now fully supports the [OpenAPI 3.0 specification](https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.0.md). Here's some of the cool stuff that you can now do with OpenAPI v3 specs:

## Validation
<img src="http://i.imgur.com/WSns2FY.png" alt="Validation: invalid 'kind' property" width="500"/></a>

## Content Assist
<img src="http://i.imgur.com/TwZ31hg.png" alt="Content Assist"  width="300"/></a>

## Quick Outline
<img src="http://i.imgur.com/l6ADPcs.png" alt="Quick Outline" width="400"/></a>

## Outline
<img src="http://i.imgur.com/44aOeuh.png" alt="Outline" width="300"/></a>

# FAQ
## How do I create a new OpenAPI v3 spec?
Please use the *OpenAPI v3 Spec* wizard from the *KaiZen OpenAPI Editor* category:


<img src="http://i.imgur.com/pTOQH8z.png" alt="File>New>Other" width="300">  <img src="https://cdn2.hubspot.net/hubfs/597611/Assets_Swagger/NewOpenAPI3Doc.png.png" alt="KaiZen OpenAPI Editor Category" width="400">

## The editor for Swagger v2 uses an underlying JSON Schema. There's no official JSON Schema for OpenAPI v3, so what are you using?
We use a modification of the JSON Schema provided by [googleapis/gnostic](https://github.com/googleapis/gnostic). We use the [SwagEdit branch](https://github.com/RepreZen/gnostic/tree/SwagEdit) to generate our JSON Schema. Thanks to @timburks and @MikeRalphson for the contribution!

