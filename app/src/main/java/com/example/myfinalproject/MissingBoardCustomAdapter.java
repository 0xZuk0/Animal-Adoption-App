package com.example.myfinalproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MissingBoardCustomAdapter extends RecyclerView.Adapter<MissingBoardCustomAdapter.MyViewHolder> {
    Context ct;
    ArrayList<MissingPet> al;

    @Override
    public int getItemCount() {
        return al.size();
    }

    public MissingBoardCustomAdapter(Context ct, ArrayList<MissingPet> al) {
        this.ct = ct;
        this.al = al;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MissingPet missingPet = al.get(position);
        holder.name.setText("Name : " + missingPet.getName());
        holder.age.setText("Age : " + String.valueOf(missingPet.getAge()) + " Year");
        byte[] image = missingPet.getImage();
        Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
        holder.image.setImageBitmap(bmp);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(ct);
        View view = layoutInflater.inflate(R.layout.mysubmissionitems, parent, false);
        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView age;
        ImageView image;
        RelativeLayout layout;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.my_submission_pet_name);
            age = (TextView) itemView.findViewById(R.id.my_submission_pet_age) ;
            image = (ImageView) itemView.findViewById(R.id.my_submission_pet_image);
            layout = (RelativeLayout) itemView.findViewById(R.id.my_submission_layout);

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    MissingPet missingPet = al.get(position);
                    Intent intent = new Intent(ct, PetViewerActivity.class);
                    intent.putExtra("name", missingPet.getName());
                    intent.putExtra("image", missingPet.getImage());
                    intent.putExtra("email", missingPet.getEmail());
                    intent.putExtra("age", missingPet.getAge());
                    intent.putExtra("address", missingPet.getAddress());
                    intent.putExtra("mobile", missingPet.getMobile());
                    intent.putExtra("description", missingPet.getDescription());
                    intent.putExtra("last_seen", missingPet.getLastSeen());
                    intent.putExtra("flag", true);
                    ct.startActivity(intent);
                }
            });

        }
    }
}