package unitec.jc.org

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class TareaSeguirPerfil (var perfil: Perfil? ): AsyncTask<Void, Void, Void>(){
    //Aqui vienen los atributos de esta clase en esta seccion, que son los objetos que
    // se van a manejar
    var estatus=Estatus()

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg params: Void?): Void? {
        //2.- Una vez colectada la infor se prepara para ser enviada al back-end, es decir el perfil
        var retrofit=Retrofit.Builder()
            .baseUrl("https://chilaquil-unitec.herokuapp.com/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
        //Con este objeto retrofit, ya podemos construir nuestro servicio
        var servicioPerfil =retrofit.create(ServicioPerfil::class.java)

        //Ahora si, invocamos el servicio que necesitamos: GUARDAR
        var envio=       servicioPerfil.guardar(perfil!!)
        estatus=envio.execute().body()!!

        return null
    }
    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        //3.- MOstramos la informacion del estatus a una componente en la capa Vista haremos uso de un Toast
        // Toast.makeText(ctx, estatus.mensaje, Toast.LENGTH_LONG).show()
        Log.i("XXX", estatus.mensaje!!)
    }
}
