package com.example.booker.ui.image;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.MimeTypeMap;

import com.example.booker.adapter.GalleryAdapter;
import com.example.booker.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Dashboard extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private GalleryAdapter adapter;
    private RecyclerView recyclerView;
    private PhotoViewModel viewModel;

    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_my_profile);

        viewModel = new ViewModelProvider(this).get(PhotoViewModel.class);
        recyclerView = findViewById(R.id.photo_booth);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new GalleryAdapter(this);

        setGallery();

        FloatingActionButton upload = findViewById(R.id.upload);

        upload.setOnClickListener(v -> uploadClicked());
    }


    private void uploadClicked() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            uri = data.getData();
        }

        upload();
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void upload() {
        if(uri != null) {
            viewModel.upload(uri, getFileExtension(uri));
        }
    }

    private void setGallery() {
        viewModel.getAllImages().observe(this, string -> {
            adapter.setImageItems(string);
            recyclerView.setAdapter(adapter);
        });
    }
}