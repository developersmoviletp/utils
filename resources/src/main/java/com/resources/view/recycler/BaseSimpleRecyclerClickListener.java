package com.resources.view.recycler;

/**
 * Created by jorgehdezvilla on 11/03/17.
 * EMP
 */

public interface BaseSimpleRecyclerClickListener<Item> {

    void onBaseItemClick(Item item);

    void onBaseItemLongClick(Item item);
}
