package unitec.jc.org.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_home.*
import unitec.jc.org.R
import unitec.jc.org.TareaGuardarPerfil


/*
Un fragmento es un elemento de android que esta asociado a SOLO UNA activity y tambien
a un Layout
En el patron de diseño MVP un fragmento viene a ser la Letra P es decir el Presenter
junto con todos las activities y el layout asociada es la View y el Modelo en este caso
la simple es la TareaAsincronica
 */

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        //Ese valor constante que aqui se genero como root nos apoyamos para
        //invocar nuestras componentes visuales e inyectarlas en el modelo
        var guardar = root.findViewById<Button>(R.id.guardar)
        //Previamente invocamos el evento (fincional) del boton y en el invocamos
        //los valores
        guardar.setOnClickListener(){
            var txtN = root.findViewById<TextInputEditText>(R.id.txtN)
            var txtP = root.findViewById<TextInputEditText>(R.id.txtP)
            var txtE =root.findViewById<TextInputEditText>(R.id.txtE)

            //Lo siguiente es lo mas interesante!! Presentar las vistas al modelo
            //Es lo que dejo de investigacion.
            //Creamos un objeto de la tarea asincronica e inicializamos sus argumentos o atributos
            //esta tarea guardar usa el modelo tradicional MVP o MVC
            TareaGuardarPerfil(txtN, txtP, txtE, root.context).execute()
        }

        return root
    }
}
