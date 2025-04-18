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

public final class ActivityPrayPlaceBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final RecyclerView rvPrayPlace;

  @NonNull
  public final Toolbar toolbarPlace;

  private ActivityPrayPlaceBinding(@NonNull CoordinatorLayout rootView,
      @NonNull RecyclerView rvPrayPlace, @NonNull Toolbar toolbarPlace) {
    this.rootView = rootView;
    this.rvPrayPlace = rvPrayPlace;
    this.toolbarPlace = toolbarPlace;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPrayPlaceBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPrayPlaceBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_pray_place, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPrayPlaceBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.rvPrayPlace;
      RecyclerView rvPrayPlace = ViewBindings.findChildViewById(rootView, id);
      if (rvPrayPlace == null) {
        break missingId;
      }

      id = R.id.toolbar_place;
      Toolbar toolbarPlace = ViewBindings.findChildViewById(rootView, id);
      if (toolbarPlace == null) {
        break missingId;
      }

      return new ActivityPrayPlaceBinding((CoordinatorLayout) rootView, rvPrayPlace, toolbarPlace);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
