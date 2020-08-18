package unitec.jc.org

import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class TareaGuardarPerfil(
    var txtN:TextInputEditText?,
    var txtP:TextInputEditText?,
    var txtE:TextInputEditText?,
    var ctx:Context
): AsyncTask <Void, Void, Void>(){
    //Aqui vienen los atributos de esta clase en esta seccion, que son los objetos que se van a
    //manejar
    var perfil=Perfil()
    var estatus=Estatus()

    override fun onPreExecute() {
        super.onPreExecute()
        //1. Colecta la informacion de la interface de usuario U.I. y forma el objeto a
        //enviarse
        perfil.nombre = txtN?.text.toString()
        perfil.patrerno = txtP?.text.toString()
        perfil.edad = txtE?.text.toString().toInt()

    }

    override fun doInBackground(vararg params: Void?): Void? {
        //2. Una vez colectada la infor se prepara para ser enviada al back-end, es decir el perfil
        var retrofit=Retrofit.Builder()
            .baseUrl("https://chilaquil-unitec.herokuapp.com")
            .addConverterFactory(JacksonConverterFactory.create())
            .build()

        //Con este objeto retrofit ya podemos construir nuestro servicio
        var servicioPerfil= retrofit.create(ServicioPerfil::class.java)

        //invocamos el servicio que necesitamos: Guardar
        var envio = servicioPerfil.guardar(perfil)
        estatus = envio.execute().body()!!
        return null
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)

        //3. Mostramos la informacion del estatus a una componente en la capa VISTA
        //haremos uso de un Toast
        Toast.makeText(ctx, estatus.mensaje, Toast.LENGTH_LONG).show()
    }
}