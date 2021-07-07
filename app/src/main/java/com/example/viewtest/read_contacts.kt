package com.example.viewtest

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.app.ActivityCompat


class read_contacts : BaseActivity() {
    private val contacts=ArrayList<String>()
    private lateinit var adpter:ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_contacts)
        load_toolbar()
        adpter= ArrayAdapter(this,android.R.layout.simple_list_item_1,contacts)
        findViewById<ListView>(R.id.contacts_list).adapter=adpter
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS),2)
        }
        else{
            readcontacts()
            Log.e("felix","read contacts ${contacts.size}")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            2->{
                if (grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    readcontacts()
                }
                else{
                    Toast.makeText(this,"You deny the permission",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    fun readcontacts(){
        contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null)?.apply {
            while(moveToNext()) {
                val name =
                    getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val num = getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                contacts.add("$name $num")
            }
            adpter.notifyDataSetChanged()
            close()
        }
    }
}