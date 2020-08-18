package unitec.jc.org.ui.slideshow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import unitec.jc.org.R
import com.mapbox.android.core.location.*
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.LocationComponentOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.lang.ref.WeakReference

class SlideshowFragment : Fragment(), OnMapReadyCallback, PermissionsListener  {

    private lateinit var slideshowViewModel: SlideshowViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //

    }

    internal inner class LocationCallbackActivity(activity:SlideshowFragment):LocationEngineCallback<LocationEngineResult>{
        //En esta parte espere el objeto de tipo callback para acceder a la localizacion en el rango establecido
        private var activityWeak: WeakReference<SlideshowFragment>?=null
        init {
            activityWeak= WeakReference(activity)
        }

        /******************************************************************************************************
         * Tercer bloque de código agregamos los siguiente métodos para
         */
        //Aqui en  el metodo  onSuccess , es donde constantemenete se esta checando la localizacion en el rango
        //que indicamos previamente en los atibutos.
        override fun onSuccess(result: LocationEngineResult?) {


            val activity: SlideshowFragment? = activityWeak!!.get()
            if (activity != null) {
                val location = result?.lastLocation ?: return

                // Cada vez que se actualice con exito una nueva localización,  aparecera este Toast
                //Puedes eliminarlo si gustas para que no apareca cada rato y este mame y mame en la pantalla del usuario
                //
                Toast.makeText(
                    activity,
                    Log.i("Lat. actualizada:"+  result.lastLocation!!.latitude.toString()),
                    Toast.LENGTH_SHORT
                ).show()

                //La nueva posicion actualiada es la variable "pos"; los dobles signos de exclamacion en kotlin implican que este valor NO ES NULO
                // esta posicion es la que debe de irse agregando a un ArrayList en tu Perfil.
                //ES LA QUE NOS INTERESA PARA FINES DE MONITOREO O TRACKING DEL USUARIO!, HAY QUE PINCHE EMOCION!!
                var pos= LatLng()
                pos.latitude=result.lastLocation!!.latitude
                pos.longitude=result.lastLocation!!.longitude
// La variable posicion es la que va a  verse reflejada y atualizada en el mapa y lo trasladamos con el método:
                //animateCamera, pudes cambiar el zoom de la camara y el metodo "tilt", es para poner que tan inclinado se muestra tu mapita
                //para que simule que estas en 3D, es una mamada pero bueno!!, se ve chingon e impresiona a los no-ingenieros.
                val posicion = CameraPosition.Builder()
                    .target(pos)
                    .zoom(18.0  )
                    .tilt(20.0)
                    .build()

                mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(posicion), 500)

                // Pasamos la nueva localizacion actualizada  al mapbox cada que se cumpla el rango de tiempo
                if (activity.mapboxMap != null && result.lastLocation != null) {
                    activity.mapboxMap.getLocationComponent()
                        .forceLocationUpdate(result.lastLocation)
                }
            }

        }

        /***********************
         *  En caso de fallo es decie excepcion dejamos al sistema de excepciones que haga su tarea, que
         *  Nos de la que crea conveniente
         *
         */
        override fun onFailure(exception: Exception) {
            val activity: SlideshowFragment? = activityWeak!!.get()
            if (activity != null) {
               Log.i("LOCAL" )
            }
        }
    }
}
