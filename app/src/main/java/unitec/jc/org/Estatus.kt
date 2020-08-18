package unitec.jc.org

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Estatus {
    var mensaje:String?=null
    var succes:Boolean?=null

}