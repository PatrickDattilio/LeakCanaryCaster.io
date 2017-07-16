package io.caster.dattilio.leakcanary;

import android.app.Fragment;


public class LeakCanaryBaseFragment extends Fragment {
  //All your fragments should extend me!

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ((LeakyApplication)getActivity().getApplication()).refwatcher.watch(this);
  }
}
