package http

import io.netty.handler.codec.http.*
import io.netty.util.CharsetUtil


object RequestUtils{

    fun formatParams(request: HttpRequest?): StringBuilder {
        val responseData = StringBuilder()
        val queryStringDecoder = QueryStringDecoder(request?.uri())
        val params: Map<String, List<String>> = queryStringDecoder.parameters()
        if (params.isNotEmpty()) {
            for ((key, vals) in params) {
                for (`val` in vals) {
                    responseData.append("Parameter: ").append(key.toUpperCase()).append(" = ")
                        .append(`val`.toUpperCase()).append("\r\n")
                }
            }
            responseData.append("\r\n")
        }
        return responseData
    }

    fun formatBody(httpContent: HttpContent): StringBuilder? {
        val responseData = StringBuilder()
        val content = httpContent.content()
        if (content.isReadable) {
            responseData.append(content.toString(CharsetUtil.UTF_8).toUpperCase())
                .append("\r\n")
        }
        return responseData
    }

    fun prepareLastResponse(request: HttpRequest?, trailer: LastHttpContent): StringBuilder? {
        val responseData = StringBuilder()
        responseData.append("Good Bye!\r\n")
        if (!trailer.trailingHeaders().isEmpty) {
            responseData.append("\r\n")
            for (name in trailer.trailingHeaders().names()) {
                for (value in trailer.trailingHeaders().getAll(name)) {
                    responseData.append("P.S. Trailing Header: ")
                    responseData.append(name).append(" = ").append(value).append("\r\n")
                }
            }
            responseData.append("\r\n")
        }
        return responseData
    }

    fun evaluateDecoderResult(o: HttpObject?): StringBuilder {
        val responseData = StringBuilder()
        val result = o?.decoderResult()
        if (result?.isSuccess != true) {
            responseData.append("..Decoder Failure: ")
            responseData.append(result?.cause())
            responseData.append("\r\n")
        }
        return responseData
    }

}
