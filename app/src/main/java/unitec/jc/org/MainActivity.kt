package unitec.jc.org

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // lo obtiene de la clase AppCompatActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //El siguiente metodo que se llama setContentView
        //Une a la activity que es esta la vista
        setContentView(R.layout.activity_main)
        //quitar la acciontoolbar
        actionBar?.hide()
        supportActionBar?.hide()

        boton1.setOnClickListener {

            //Navegaci[on para pasar de la activity MainActivity a la MenuActivity
            var i = Intent(this, MenuActivity::class.java)
            //Finalmente redireccionamos
            startActivity(i)

            // tenemos el manejo del evento
            //vamos a mostrar en pantalla nuestro primer objeto con codigo
            Toast.makeText(this, "Mi primer app con kotlin", Toast.LENGTH_LONG)
                .show()
            //Vamos a invocar por medio del contexto el dispositivo
            // de vibracion de cel
            var v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            //El objeto v ya es de tipo Vibrator invocamos su metodo vibrate
            v.vibrate(3000)

            // En kotlin no existen ya los datos primitivos o primarios
            //float, int, char, double, byte, short
            //Declaracion de constante
            val y: Int = 7
            //y = 5
            var z:Int;
            z = 9

            //La clase Log sirve para accerder a esta ventana
            //La verdad que la amamos los developers en anroid es para depurar
            //El signo de pesos seguido de una variable se le llama interpolacion estatica de String
            Log.v("CHORO", "El valor de z es $z y el de y es $y")
            //Tambien el signo de $ sirve para evaluar e interpolar expresiones de programacion
            Log.i("CHORO", "Una expresion evalua simple ${z+2}")

            //Invocamos la funcion
            hola()
            Log.i("CHORO", "Invocando una funcion con tipo de retorno ${hola2()}")
            Log.i("CHORO", "El valor es entre 20 y 30 ${hola3(95,1.73f)}")
        }
    }
    //Aqui vamos a declarar una funcion (metodos)
    fun  hola(){
       Log.i("CHORO", "Una funci[on invocada")
    }
    //Otra funcion
    fun hola2():String{
        return "Hola mundo"
    }
    //Con parametros o argumentos
    fun hola3(peso: Int, altura:Float):Float{
        var imc = peso/(altura*altura)
        return imc
    }
}
