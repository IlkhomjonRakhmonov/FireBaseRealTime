package uz.rakhmonov.firebaserealtime

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.rakhmonov.firebaserealtime.databinding.RvItemBinding
import uz.rakhmonov.firebaserealtime.models.Mymessege

class RV_adapter (val list:ArrayList<Mymessege> = ArrayList()):RecyclerView.Adapter<RV_adapter.VH>(){

    inner class VH (val rvItemBinding: RvItemBinding): RecyclerView.ViewHolder(rvItemBinding.root){
        fun onHolder(mymessege: Mymessege,position:Int){
            rvItemBinding.id.text=mymessege.id.toString()
            rvItemBinding.text.text=mymessege.text.toString()
            rvItemBinding.date.text=mymessege.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(RvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onHolder(list[position],position)

    }

    override fun getItemCount(): Int=list.size




}