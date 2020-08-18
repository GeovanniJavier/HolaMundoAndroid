package unitec.jc.org.ui.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import unitec.jc.org.ApiServicioPerfil
import unitec.jc.org.Perfil


//El view model se encarga de obtener la informacion del back-end
//y prepararla para la vista. En el caso del RecycleView vamos a tener
//que apoyarnos de las clases adaptadoras. Su funcionn de ellas es "Adaptar" el modelo
//a la vista (perfil_item) javiiiiiiiiiiiiiiiiiii
class GalleryViewModel : ViewModel() {

    val lista_perfiles = MutableLiveData<List<Perfil>>()
    val errorInternet = MutableLiveData<Boolean>()
    val cargando = MutableLiveData<Boolean>()
    //esta varible es para el manejo de memoria en arquitectura reactiva
    private val dispose=CompositeDisposable()

    //Delcaramos el atributo del apoServicio Perfil que ya es reactiva
    private val apiServicioPerfil=ApiServicioPerfil()

    private fun obtenerPerfiles(){
    //Aqui vamos a llamar nuestro servicio rest, por el momento vamos a
    //hacer un fake de perfiles para primero, verificar que funcione
    }
    fun refrescar(){
        //Inicalmente queremos que el error de internet sea falso
        errorInternet.value=false
        obtenerListadoRemotamente()

    }
    //Este nuevo metedo es el de la conexcion asincronica ractiva
    fun obtenerListadoRemotamente(){
        cargando.value=true

        //Aqui viene el thread asincronico RECTIVO HUUUY QUE MIEDOOOOO TODO MUY BUENO
        //error javiiiiiiiiiiiiiiiiiii
        dispose.add(
            apiServicioPerfil.getPerfiles()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Perfil>>(){
                    override fun onSuccess(listaPerf: List<Perfil>?) {
                       lista_perfiles.value=listaPerf
                        cargando.value=false
                    }

                    override fun onError(e: Throwable?) {
                        errorInternet.value=true
                        cargando.value=false
                    }

                })
        )
    }
}