package com.finerioconnect.widget.bank;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.finerioconnect.widget.R;
import com.finerioconnect.widget.remote.data.Bank;

//import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
//import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener;

class BankMagicLinkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final BanksClickListener mBanksClickListener;
    private final ImageView mImageView;
    private final Activity mActivity;
    private Bank mBank;

    public BankMagicLinkViewHolder(@NonNull View itemView, Activity activity, BanksClickListener banksClickListener) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.categoryImageView);
        mActivity = activity;
        mBanksClickListener = banksClickListener;
    }

    public void setBankRow(Bank bank) {
        mBank = bank;
        mImageView.setOnClickListener(this);

        /*if (bank.getName().equals("Liverpool")){
            mImageView.setImageResource(R.drawable.ic_liverpool_on);
            return;
        }*/

        Glide.with(mActivity).load(bank.getUrlImageOff()).into(mImageView);
        /*
        GlideToVectorYou
                .init()
                .with(mActivity)
                .withListener(new GlideToVectorYouListener() {
                    @Override
                    public void onLoadFailed() {
                        if (bank.getName().equals("Liverpool")){
                            mImageView.setImageResource(R.drawable.ic_liverpool_on);
                        }
                    }

                    @Override
                    public void onResourceReady() {
                        //Toast.makeText(mActivity, "Image ready", Toast.LENGTH_SHORT).show();
                    }
                })
                //.setPlaceHolder(placeholderLoading, placeholderError)
                .load(Uri.parse(bank.getUrlImageOn()), mImageView);*/
    }

    @Override
    public void onClick(View view) {
        mBanksClickListener.getOnClickListener(mBank);
    }

}
