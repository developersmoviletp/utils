package com.resources.view.textbox;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.resources.R;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;

import rx.Observable;


/**
 * Created by danielgarcia on 8/5/16.
 * MobileAdjustment
 */
@SuppressWarnings({"unchecked"})
public class SimpleCatalogEditText<T> extends AppCompatEditText {

    CharSequence mHint;
    OnCatalogSelectedListener<T> onCatalogSelectedListener;
    ListAdapter mSpinnerAdapter;
    public List<T> mItems;
    private T mSelectedObject;

    public SimpleCatalogEditText(Context context) {
        super(context);
        mHint = getHint();
    }

    public SimpleCatalogEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHint = getHint();
    }

    public SimpleCatalogEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHint = getHint();
    }

    public void setCatalogs(List<T> catalogs) {
        mItems = catalogs;
        setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mItems));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setFocusable(false);
        setClickable(true);
    }

    public void setAdapter(ListAdapter adapter) {
        mSpinnerAdapter = adapter;
        configureOnClickListener();
    }

    private void configureOnClickListener() {
        setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

            @SuppressLint("InflateParams")
            View dialogView = LayoutInflater.from(getContext())
                    .inflate(R.layout.view_catalog_edit_text, null);
            TextView titleTextView = (TextView) dialogView.findViewById(R.id.v_catalog_edit_text_title);
            final ListView listView = (ListView) dialogView.findViewById(R.id.v_catalog_edit_text_list);
            EditText searchEditText = (EditText) dialogView.findViewById(R.id.v_catalog_edit_text_search);

            titleTextView.setText(mHint);
            listView.setAdapter(mSpinnerAdapter);
            builder.setView(dialogView);
            builder.setPositiveButton(R.string.dialog_cancel, null);

            final AlertDialog dialog = builder.create();

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    T object = (T) listView.getAdapter().getItem(position);
                    mSelectedObject = object;
                    setText(object.toString());
                    if (onCatalogSelectedListener != null) {
                        onCatalogSelectedListener.onItemCatalogSelectedListener(object, position);
                    }
                    dialog.dismiss();
                }
            });
            RxTextView.afterTextChangeEvents(searchEditText)
                    .subscribe(event ->
                    {
                        String search = event.view().getText().toString().trim();
                        if (TextUtils.isEmpty(search)) {
                            listView.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mItems));
                        } else {
                            Observable.from(mItems)
                                    .filter(t ->
                                            t.toString().toLowerCase().contains(search.toLowerCase())
                                    )
                                    .toList()
                                    .subscribe(ts -> listView.setAdapter(new ArrayAdapter<T>(getContext(), android.R.layout.simple_list_item_1, ts)));
                        }
                    });
            dialog.show();
        });
    }

    public void setOnCatalogSelectedListener(OnCatalogSelectedListener<T> onCatalogSelectedListener) {
        this.onCatalogSelectedListener = onCatalogSelectedListener;
    }

    public void setSelectedObject(T object) {
        if (object != null) {
            mSelectedObject = object;
            setText(object.toString());
        }
    }


    public interface OnCatalogSelectedListener<T> {
        void onItemCatalogSelectedListener(T catalogItem, int selectedIndex);
    }

    public T getSelectedValue() {
        return mSelectedObject;
    }

}
