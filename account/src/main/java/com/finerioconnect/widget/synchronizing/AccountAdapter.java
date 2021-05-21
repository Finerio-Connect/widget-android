package com.finerioconnect.widget.synchronizing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.finerioconnect.widget.R;
import com.finerioconnect.widget.remote.data.Account;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private final List<Account> mAccountRows;

  public AccountAdapter(List<Account> accountRows) {
    this.mAccountRows = accountRows;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType ) {
    View view = LayoutInflater.from( parent.getContext() )
        .inflate( R.layout.item_account, parent, false );
    return new AccountViewHolder( view );
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position ) {
    Account accountRow = mAccountRows.get( position );
    AccountViewHolder bankViewHolder = (AccountViewHolder) viewHolder;
    bankViewHolder.setAccountRow( accountRow );
  }

  @Override
  public int getItemCount() {
    return mAccountRows.size();
  }

  @Override
  public int getItemViewType( int position ) {
    return 0;
  }

  public void updateList(List<Account> accountRows){

    for(Account account: accountRows){

      String id = account.getId();
      String name = account.getName();
      String status = account.getStatus();
      boolean found = false;

      for ( Account itemAccount: mAccountRows ) {

        if ( id.equals(itemAccount.getId()) ){
          found = true;
          itemAccount.setStatus(status);
          break;
        }

      }

      if ( !found ){ mAccountRows.add( new Account(id, status, name) ); }

    }

  }

}

