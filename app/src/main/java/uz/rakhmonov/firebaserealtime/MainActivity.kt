package uz.rakhmonov.firebaserealtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import uz.rakhmonov.firebaserealtime.databinding.ActivityMainBinding
import uz.rakhmonov.firebaserealtime.models.Mymessege
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var reference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var rvAdapter: RV_adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()
        reference = database.getReference("xabarlar")

        binding.btnSend.setOnClickListener {
            val key = reference.push().key
            reference.child(key!!).setValue(
                Mymessege(
                    key,
                    binding.edtMessege.text.toString(),
                    SimpleDateFormat("dd.MM.yyyy    hh:mm:SS").format(
                        Date()
                    )
                )
            )
            binding.edtMessege.text.clear()
            Toast.makeText(this, "send messege", Toast.LENGTH_SHORT).show()
        }
        loadData()
    }

    private fun loadData() {
        rvAdapter= RV_adapter()
        binding.rv.adapter=rvAdapter
        reference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val mylist=java.util.ArrayList<Mymessege>()
                val children=snapshot.children
                for (child in children){
                    val value=child.getValue(Mymessege::class.java)
                    if (value!=null){
                      mylist.add(value)
                  }
                }
                rvAdapter.list.clear()
                rvAdapter.list.addAll(mylist)
                rvAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}