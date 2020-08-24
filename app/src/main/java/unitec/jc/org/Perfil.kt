package unitec.jc.org
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Perfil {
    var id:String?=null
    var nombre:String?=null
    var patrerno:String?=null
    var email:String?=null
    var celular:String?=null
    var edad:Int?=null
    var localizaciones:List<Localizacion>?=null
}