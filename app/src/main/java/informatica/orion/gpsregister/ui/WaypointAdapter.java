package informatica.orion.gpsregister.ui;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import informatica.orion.gpsregister.databinding.WaypointItemBinding;
import informatica.orion.gpsregister.model.Waypoint;
import informatica.orion.gpsregister.R;

import java.util.List;
import java.util.Objects;

public class WaypointAdapter extends RecyclerView.Adapter<WaypointAdapter.WaypointViewHolder> {

    private List<? extends Waypoint> mWaypointList;

    @Nullable
    private final WaypointClickCallback mWaypointClickCallback;

    public WaypointAdapter(@Nullable WaypointClickCallback waypointClickCallback) {
        mWaypointClickCallback = waypointClickCallback;
    }

    public void setWaypointList(final List<? extends Waypoint> waypoints) {
        if (mWaypointList == null) {
            mWaypointList = waypoints;
            notifyItemRangeInserted(0, waypoints.size());
        } else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mWaypointList.size();
                }

                @Override
                public int getNewListSize() {
                    return waypoints.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    Waypoint old = mWaypointList.get(oldItemPosition);
                    Waypoint waypoint = waypoints.get(newItemPosition);
                    return old.getId() == waypoint.getId();
                }

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Waypoint old = mWaypointList.get(oldItemPosition);
                    Waypoint waypoint = waypoints.get(newItemPosition);
                    return old.getId() == waypoint.getId()
                            && old.getCedula() == waypoint.getCedula()
                            && old.getCoordLAT() == waypoint.getCoordLAT()
                            && old.getCoordLONG() == waypoint.getCoordLONG()
                            && old.getCoordX() == waypoint.getCoordX()
                            && old.getCoordY() == waypoint.getCoordY()
                            && old.getFecha() == waypoint.getFecha()
                            && old.getOt() == waypoint.getOt()
                            && old.getNis() == waypoint.getNis()
                            && Objects.equals(old.getObservacion(), waypoint.getObservacion());
                }
            });
            mWaypointList = waypoints;
            diffResult.dispatchUpdatesTo(this);
        }
    }

    @Override
    public WaypointViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        WaypointItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.waypoint_item,
                        parent, false);
        binding.setCallback(mWaypointClickCallback);
        return new WaypointViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(WaypointViewHolder holder, int position) {
        holder.binding.setWaypoint(mWaypointList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mWaypointList == null ? 0 : mWaypointList.size();
    }

    static class WaypointViewHolder extends RecyclerView.ViewHolder {

        final WaypointItemBinding binding;

        WaypointViewHolder(WaypointItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}