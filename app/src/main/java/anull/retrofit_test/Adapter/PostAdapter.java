package anull.retrofit_test.Adapter;

import android.os.Build;
import android.support.v7.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import anull.retrofit_test.Model.PostModel;
import anull.retrofit_test.R;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<PostModel> mPosts;

    public PostAdapter(List<PostModel> posts) {
        this.mPosts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onBindViewHolder(ViewHolder holder, int position) {
        PostModel post = mPosts.get(position);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.tvPost.setText(Html.fromHtml(post.getElementPureHtml(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.tvPost.setText(Html.fromHtml(post.getElementPureHtml()));
        }
        holder.tvSite.setText(post.getSite());
    }

    @Override
    public int getItemCount() {
        if (mPosts == null)
            return 0;
        return mPosts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPost;
        TextView tvSite;

        ViewHolder(View itemView) {
            super(itemView);
            tvPost = itemView.findViewById(R.id.postitem_post);
            tvSite = itemView.findViewById(R.id.postitem_site);
        }
    }
}
