package net.odiak.playground

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private var imageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView) as ImageView
    }

    override fun onResume() {
        super.onResume()

        Picasso.with(this).load("http://file.odiak.net/images/test.png")
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .networkPolicy(NetworkPolicy.NO_STORE)
                .into(imageView)
    }
}
