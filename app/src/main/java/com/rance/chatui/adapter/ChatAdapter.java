package com.rance.chatui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.rance.chatui.adapter.holder.ChatAcceptViewHolder;
import com.rance.chatui.adapter.holder.ChatSendViewHolder;
import com.rance.chatui.enity.MessageInfo;

/**
 * 作者：Rance on 2016/11/29 10:46
 * 邮箱：rance935@163.com
 */
public class ChatAdapter extends RecyclerArrayAdapter<MessageInfo> {

    private onItemClickListener onItemClickListener;

    public ChatAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder = null;
        switch (viewType) {
            case 1:
                viewHolder = new ChatAcceptViewHolder(parent, onItemClickListener);
                break;
            case 2:
                viewHolder = new ChatSendViewHolder(parent, onItemClickListener);
                break;
        }
        return viewHolder;
    }

    @Override
    public int getViewType(int position) {
        return getAllData().get(position).getType();
    }

    public void addItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface onItemClickListener {
        void onHeaderClick(int position);

        void onImageClick(int position);

        void onVoiceClick(ImageView imageView, int position);
    }
}
