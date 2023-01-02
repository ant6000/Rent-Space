package com.example.too_let_final;

import static android.app.Activity.RESULT_OK;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class UploadFragment extends Fragment {

    private Button selectImagebtn, submitbtn;
    private ImageView imageView;
    private TextView bed,bath,kitchen,dining,rent,description,location;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private Uri imageUri;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upload, container, false);
        // initialize all view
        selectImagebtn = (Button) view.findViewById(R.id.selectBtn);
        submitbtn = (Button) view.findViewById(R.id.submitBtn);
        imageView =(ImageView)view.findViewById(R.id.imageView_);
        bed = (TextView) view.findViewById(R.id.bed_);
        bath = (TextView) view.findViewById(R.id.bath_);
        kitchen = (TextView) view.findViewById(R.id.kitchen_);
        dining = (TextView) view.findViewById(R.id.dining_);
        rent = (TextView) view.findViewById(R.id.rent_);
        description = (TextView) view.findViewById(R.id.description_);
        location = (TextView) view.findViewById(R.id.location_);

        // Initialize firebase database
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("NewUserData");
        storageReference = FirebaseStorage.getInstance().getReference();



        selectImagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 2);
            }
        });

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUri !=null){
                    uploadToFirebase(imageUri);
                }
                else {
                    //Toast.makeText(this,"Please select an image",Toast.LENGTH_LONG).show();
                    Toast.makeText(getActivity(), "Please select image and fill up all the fields", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            imageUri =data.getData();
            imageView.setImageURI(imageUri);
        }
    }

    private void uploadToFirebase( Uri uri){
        String Bed = bed.getText().toString();
        String Bath = bath.getText().toString();
        String Kitchen = kitchen.getText().toString();
        String Dining = dining.getText().toString();
        String Rent = rent.getText().toString() ;
        String Description = description.getText().toString();
        String Location = location.getText().toString();
        //Store data to firebase
        StorageReference fileref = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess( Uri uri) {
                        HouseDetails houseDetails = new HouseDetails(Bed,Bath,Dining,Kitchen,Rent,Description,Location,uri.toString());
                        String modelId = databaseReference.push().getKey();
                        databaseReference.child(modelId).setValue(houseDetails);
                         //Toast.show(UploadFragment.this, "uploaded Successfully", Toast.LENGTH_LONG).show();
                        Toast.makeText(getActivity(), "Uploaded Successfully", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Toast.makeText(MainActivity.this, "Uploading Failed", Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Uploading Failed", Toast.LENGTH_LONG).show();
            }
        });
    }
    private String getFileExtension(Uri muri) {
        ContentResolver cr = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(muri));
    }
}