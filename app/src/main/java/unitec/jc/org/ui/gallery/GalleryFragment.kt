package unitec.jc.org.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_gallery.*
import unitec.jc.org.R

class GalleryFragment : Fragment() {
    //Empezamos declaran dos atributos, el primero es view model, y el segundo de tipo adapter
    //recien creado
    private lateinit var viewModel: GalleryViewModel
    private val perfilListAdapter=PerfilAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_gallery, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Aqui hacemos el enlace de nuestros atributos
        viewModel=ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        //Inmediatamente le vamos a decir que se conecte al back-end
        viewModel.refrescar()

        //hasta aqui en ningun momento el layout perfilitem esta ligado al fragmet_gallery
        //Primero va el enlace
        perfiles.apply {
            layoutManager=LinearLayoutManager(context)
            adapter = perfilListAdapter
        }
        //Aqui vamos a invocar un metodo que de manera automatica lleva acabo la adaptacion
        //cada que el usuario le de click al mennu donde esta el fragmento de la gallery
        //este paso es el equivalente de cuando se meten a la app de facebook y se refresca
        //y cambia los datos en automatico.

        //activamos el swipe
        swipe.setOnRefreshListener {
            perfiles.visibility=View.GONE
            error.visibility=View.GONE
            cargando.visibility=View.VISIBLE
            viewModel.refrescar()
            swipe.isRefreshing=false
        }

        observarViewModel()
    }
    //El ultimo y nos vamos este metodo implementa a su vez unpatron de dise;o reactivo
    //que se llama observer
    fun observarViewModel(){
        //primero invocamos nuestro atributo viewmodel
        viewModel.lista_perfiles.observe(this, Observer {perfi ->
            perfi?.let {
                perfiles.visibility=View.VISIBLE
                perfilListAdapter.actualizarPerfilList(perfi)
            }
        })
        //seguimos con el errorInternet
        viewModel.errorInternet.observe(this, Observer {errorcito ->
            errorcito?.let {
                error.visibility=if(it)View.VISIBLE else View.GONE
            }
        })
        //Terminamos con cargando
        viewModel.cargando.observe(this, Observer {carga ->
            carga?.let {
                cargando.visibility=if (it)View.VISIBLE else View.GONE
            }

        })
    }
}
