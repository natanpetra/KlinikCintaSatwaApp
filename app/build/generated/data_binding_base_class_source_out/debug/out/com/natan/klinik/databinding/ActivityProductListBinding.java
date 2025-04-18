// Generated by view binder compiler. Do not edit!
package com.natan.klinik.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.natan.klinik.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityProductListBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final RecyclerView rvProduct;

  @NonNull
  public final Toolbar toolbarHotel;

  private ActivityProductListBinding(@NonNull CoordinatorLayout rootView,
      @NonNull RecyclerView rvProduct, @NonNull Toolbar toolbarHotel) {
    this.rootView = rootView;
    this.rvProduct = rvProduct;
    this.toolbarHotel = toolbarHotel;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityProductListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityProductListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_product_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityProductListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.rvProduct;
      RecyclerView rvProduct = ViewBindings.findChildViewById(rootView, id);
      if (rvProduct == null) {
        break missingId;
      }

      id = R.id.toolbar_hotel;
      Toolbar toolbarHotel = ViewBindings.findChildViewById(rootView, id);
      if (toolbarHotel == null) {
        break missingId;
      }

      return new ActivityProductListBinding((CoordinatorLayout) rootView, rvProduct, toolbarHotel);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
