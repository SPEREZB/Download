package com.example.download

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import com.example.download.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var  binding:ActivityMainBinding
    private  lateinit var imageView: ImageView
    private lateinit var capturarbtn: Button
    private lateinit var out:TextView
    private var codigo=123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)

        imageView= binding.img
        capturarbtn= binding.capturarimg
        out= binding.out
        val cargar= binding.cargarimg

        capturarbtn.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED)
            {
                takePicturePreview.launch(null)
            }
            else
            {
                requestPermissions.launch/
            }
        }
        cargar.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED)
            {
                val intent= Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                intent.type ="image/*"
                val mimeTypes= arrayOf("image/png","image.jpg","image.jpeg")
                intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes)
                intent.flags=Intent.FLAG_GRANT_READ_URI_PERMISSION

            }
        }
    }
   //request permisos camara
    private val requestPermissions= registerForActivityResult(ActivityResultContracts.RequestPermission())
   {
        granted-> if(granted){
            takePicturePreview.launch(null)
   }else
   {
       Toast.makeText(this,"Permiso denegado intenete denuevo", Toast.LENGTH_SHORT).show()
   }

   }


        //launch camaera and take picture
    private val takePicturePreview= registerForActivityResult(ActivityResultContracts.TakePicturePreview()){
        bitmap->if(bitmap!==null)
    {
            imageView.setImageBitmap(bitmap)
           outGenerador(bitmap)
    }
    }
    private var lam: (a:String) -> Int={

    }

    //tensorflow



}