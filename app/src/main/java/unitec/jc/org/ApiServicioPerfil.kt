package unitec.jc.org

import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

class ApiServicioPerfil {
    //Aqu[i declaramos la URLbase de nuestra api
    private val baseUrl = "https://chilaquil-unitec.herokuapp.com/"

    //la siguiente variable es el objeto retrofit para acceder al servicio
    private val api=Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(JacksonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(ServicioPerfil::class.java)

    //Generamos una funcion para invocar a todos desde aqu[i
    //Si  puedes mas adealnte, invocar algo pero para el de get por id

    fun getPerfiles():Single<List<Perfil>>{
        return api.buscarTodos()
    }
    /*fun getPerfiles():Single<List<Perfil>>{
        return api.buscarTodos()
    }*/
}