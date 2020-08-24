package unitec.jc.org

import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.*

//Esta interfaz es el extremo del front-end para comunicarse con el backend
//Tu extremo de comunicacion en el back-end es el ControladorPerfil
//de tal manera que POR CADA SERVICIO EN EL CONTROLADOR, DEBE DE HABER UN SERVICIO
//ESPEJO AQUI. Es decir 5 operaciones basicas CRUD de la entidad Perfil

interface ServicioPerfil {
    //Comenzamos con el POST
    @POST("api/perfil")
    fun guardar(@Body perfil:Perfil):Call<Estatus>

    //Sigue buscar todos
    @GET("api/perfil")
    fun buscarTodos():Single<List<Perfil>>

    //Sigue el de buscar por ID
    @GET("api/perfil/{id}")
    fun buscarTodos(@Part("id") id:String?):Call<Perfil>

    //Actualizaar
    @PUT("api/perfil")
    fun actualziar(@Body perfil:Perfil):Call<Estatus>

    //Borrar por ID
    @DELETE("api/perfil/{id}")
    fun borrar(@Path("id") id:String?):Call<Estatus>
}