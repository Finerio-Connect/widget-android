package com.finerioconnect.widget.credentials;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import com.finerioconnect.widget.R;
import com.finerioconnect.widget.remote.data.Field;

public class FieldsAdapter extends RecyclerView.Adapter<FieldsAdapter.ViewHolder> {

    private final Context context;
    private final List<Field> arrayList;

    public FieldsAdapter(Context context, List<Field> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_row_field, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
        Field mField = arrayList.get(position);

        holder.tvTitle.setText(mField.getFriendlyName());
        holder.editTextValue.setHint(mField.getFriendlyName());

        if (mField.getType().equals("PASSWORD")){
            holder.layoutTextInput.setEndIconMode(TextInputLayout.END_ICON_PASSWORD_TOGGLE);
            holder.layoutTextInput.setEndIconDrawable(R.drawable.show_and_hide_password);
        }

        if (!TextUtils.isEmpty(mField.getUserName())) {
            holder.editTextValue.setText(mField.getUserName());
        }

        if (!TextUtils.isEmpty(mField.getError())) {
            holder.tvErrorMessage.setVisibility(View.VISIBLE);
            holder.tvErrorMessage.setText(mField.getError());
            holder.editTextValue.setBackgroundResource(R.drawable.bg_input_error);
        } else {
            holder.editTextValue.setBackgroundResource(R.drawable.bg_input_success);
            holder.tvErrorMessage.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public List<Field> getArrayList() {
        return arrayList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        EditText editTextValue;
        TextView tvTitle;
        TextView tvErrorMessage;
        TextInputLayout layoutTextInput;

        public ViewHolder(View itemView) {
            super(itemView);

            layoutTextInput = itemView.findViewById(R.id.layoutTextInput);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvErrorMessage = itemView.findViewById(R.id.tvErrorMessage);
            editTextValue = itemView.findViewById(R.id.editTextValue);
            editTextValue.addTextChangedListener(new TextWatcher() {
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    arrayList.get(getAdapterPosition()).setUserName(charSequence.toString());
                }
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
                @Override
                public void afterTextChanged(Editable editable) { }

            });

        }
    }
}