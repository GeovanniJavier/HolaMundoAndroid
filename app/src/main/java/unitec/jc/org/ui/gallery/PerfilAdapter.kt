package unitec.jc.org.ui.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.perfil_item.view.*
import unitec.jc.org.Perfil
import unitec.jc.org.R

//La clase PerfilViewHolder es realmente la clase que sirve de enlace entre el modelo y la vista
class PerfilAdapter(val perfilList: ArrayList<Perfil>): RecyclerView.Adapter<PerfilAdapter.PerfilViewHolder>() {



    //Generamos esa clase, que en este patron MVM debe ser como clase interna
    class PerfilViewHolder(var view:View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerfilViewHolder {
        var inflater = LayoutInflater.from(parent.context)

        val view = inflater.inflate(R.layout.perfil_item, parent, false)
        return PerfilViewHolder(view)
    }

    //aqui ponemos el perfil adapter de la actualizacion
    fun actualizarPerfilList(nuevaListaPerfil:List<Perfil>){
        //Aqui viene la actualizacion automatica
        perfilList.clear()
        perfilList.addAll(nuevaListaPerfil)

        //este metodo final notifica al observer que esta en la otra clase
        //la notificacion de que hay una actualizacion
        notifyDataSetChanged()

    }

    override fun getItemCount()=perfilList.size

    override fun onBindViewHolder(holder: PerfilViewHolder, position: Int) {
        //Bind en inlges es enlazar, este metodo nos enlaza cada pista a tu modelo
        //Aqui iran el nombre, el paterno, mail, edad, de perfil
        //Solamente pondremos por el momento en nombre, porque en el list fake que
        //Pusimos pues solamente pusimos
        holder.view.nombre_item.text= perfilList[position].nombre
        //Para cada uno de los ide's del perfil item tenemos que agregar algo similar

    }
}