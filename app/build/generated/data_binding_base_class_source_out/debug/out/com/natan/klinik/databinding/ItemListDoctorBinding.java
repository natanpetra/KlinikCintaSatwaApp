// Generated by view binder compiler. Do not edit!
package com.natan.klinik.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.natan.klinik.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemListDoctorBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final CardView cvDoctor;

  @NonNull
  public final ImageView imgDoctor;

  @NonNull
  public final TextView tvName;

  @NonNull
  public final TextView tvSpecialist;

  private ItemListDoctorBinding(@NonNull CardView rootView, @NonNull CardView cvDoctor,
      @NonNull ImageView imgDoctor, @NonNull TextView tvName, @NonNull TextView tvSpecialist) {
    this.rootView = rootView;
    this.cvDoctor = cvDoctor;
    this.imgDoctor = imgDoctor;
    this.tvName = tvName;
    this.tvSpecialist = tvSpecialist;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemListDoctorBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemListDoctorBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_list_doctor, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemListDoctorBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      CardView cvDoctor = (CardView) rootView;

      id = R.id.imgDoctor;
      ImageView imgDoctor = ViewBindings.findChildViewById(rootView, id);
      if (imgDoctor == null) {
        break missingId;
      }

      id = R.id.tvName;
      TextView tvName = ViewBindings.findChildViewById(rootView, id);
      if (tvName == null) {
        break missingId;
      }

      id = R.id.tvSpecialist;
      TextView tvSpecialist = ViewBindings.findChildViewById(rootView, id);
      if (tvSpecialist == null) {
        break missingId;
      }

      return new ItemListDoctorBinding((CardView) rootView, cvDoctor, imgDoctor, tvName,
          tvSpecialist);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
