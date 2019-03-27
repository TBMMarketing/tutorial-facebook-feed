package co.tiagoaguiar.facebook;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private PostAdapter postAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    TabLayout tabLayout = findViewById(R.id.top_nav);
    tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.feed));
    tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.request));
    tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.users));
    tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.watch));
    tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.notify));
    tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.more));

    RecyclerView rv = findViewById(R.id.recycler_view);

    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(),
            DividerItemDecoration.VERTICAL);
    dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
    rv.addItemDecoration(dividerItemDecoration);

    rv.setLayoutManager(new LinearLayoutManager(this));
    postAdapter = new PostAdapter();
    rv.setAdapter(postAdapter);

    List<Post> posts = new ArrayList<>();
    Post post1 = new Post();

    post1.setImageViewUser(R.drawable.jon_snow);
    post1.setImageViewPost(R.drawable.post_1);
    post1.setTextViewTime("2 min");
    post1.setTextViewUsername("Jon Snow");
    post1.setTextViewContent("No matter what Ygritte says, Jon Snow knows a few things. The bastard son of Ned Stark (or is he?) grew up in a household where he was welcomed by Ned and his half-siblings but not so much by Catelyn Stark. When the opportunity came to join the Night's Watch at Castle Black, he saw");
    post1.setTextViewTitle("gameofthrones.com".toUpperCase());
    post1.setTextViewSubtitle("Game of Thrones is an American fantasy drama television series created by David Benioff and D. B. Weiss. It is an adaptation of A Song of Ice and Fire, George R. R. Martin's series of fantasy novels, the first of which is A Game of Thrones");
    posts.add(post1);


    Post post2 = new Post();
    post2.setImageViewUser(R.drawable.red_queen);
    post2.setImageViewPost(R.drawable.post_2);
    post2.setTextViewTime("12 min");
    post2.setTextViewUsername("Melisandre");
    post2.setTextViewContent("My ability to see visions in the flames, and completely trusts in the power of her god, R'hllor. Although she acknowledges that visions can be misinterpreted,[8] she has faith in her ability to correctly interpret visions, even if the vision does not entirely agree with the proposed interpretation.[4] ");
    posts.add(post2);

    postAdapter.setPosts(posts);
    postAdapter.notifyDataSetChanged();
  }

  private static class PostViewHolder extends RecyclerView.ViewHolder {

    private final ImageView imageViewUser;
    private final TextView textViewTime;
    private final TextView textViewUsername;
    private final TextView textViewContent;
    private final ImageView imageViewPost;
    private final TextView textViewTitle;
    private final TextView textViewSubtitle;

    PostViewHolder(@NonNull View itemView) {
      super(itemView);
      imageViewUser = itemView.findViewById(R.id.image_view_user);
      textViewTime = itemView.findViewById(R.id.text_view_time);
      textViewUsername = itemView.findViewById(R.id.text_view_username);
      textViewContent = itemView.findViewById(R.id.text_view_content);
      imageViewPost = itemView.findViewById(R.id.image_view_post);
      textViewTitle = itemView.findViewById(R.id.text_view_post_title);
      textViewSubtitle = itemView.findViewById(R.id.text_view_post_subtitle);
    }

    void bind(Post post) {
      imageViewUser.setImageResource(post.getImageViewUser());
      textViewTime.setText(post.getTextViewTime());
      textViewUsername.setText(post.getTextViewUsername());
      textViewContent.setText(post.getTextViewContent());
      imageViewPost.setImageResource(post.getImageViewPost());
      textViewTitle.setText(post.getTextViewTitle());
      textViewSubtitle.setText(post.getTextViewSubtitle());

      if (post.getTextViewTitle() == null) {
        itemView.findViewById(R.id.post_container).setVisibility(View.GONE);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone((ConstraintLayout) itemView);
        constraintSet.setDimensionRatio(R.id.image_view_post, "1:1");
        constraintSet.applyTo((ConstraintLayout) itemView);
      } else {
        itemView.findViewById(R.id.post_container).setVisibility(View.VISIBLE);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone((ConstraintLayout) itemView);
        constraintSet.setDimensionRatio(R.id.image_view_post, "16:9");
        constraintSet.applyTo((ConstraintLayout) itemView);
      }
    }

  }

  private class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private List<Post> posts = new ArrayList<>();

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      View view = getLayoutInflater().inflate(R.layout.feed_item, viewGroup, false);
      return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder postViewHolder, int i) {
      Post post = posts.get(i);
      postViewHolder.bind(post);
    }

    @Override
    public int getItemCount() {
      return posts.size();
    }

    void setPosts(List<Post> posts) {
      this.posts = posts;
    }

  }

  private class Post {

    private int imageViewUser;
    private String textViewTime;
    private String textViewUsername;
    private String textViewContent;
    private int imageViewPost;
    private String textViewTitle;
    private String textViewSubtitle;

    public int getImageViewUser() {
      return imageViewUser;
    }

    public void setImageViewUser(int imageViewUser) {
      this.imageViewUser = imageViewUser;
    }

    public String getTextViewTime() {
      return textViewTime;
    }

    public void setTextViewTime(String textViewTime) {
      this.textViewTime = textViewTime;
    }

    public String getTextViewUsername() {
      return textViewUsername;
    }

    public void setTextViewUsername(String textViewUsername) {
      this.textViewUsername = textViewUsername;
    }

    public String getTextViewContent() {
      return textViewContent;
    }

    public void setTextViewContent(String textViewContent) {
      this.textViewContent = textViewContent;
    }

    public int getImageViewPost() {
      return imageViewPost;
    }

    public void setImageViewPost(int imageViewPost) {
      this.imageViewPost = imageViewPost;
    }

    public String getTextViewTitle() {
      return textViewTitle;
    }

    public void setTextViewTitle(String textViewTitle) {
      this.textViewTitle = textViewTitle;
    }

    public String getTextViewSubtitle() {
      return textViewSubtitle;
    }

    public void setTextViewSubtitle(String textViewSubtitle) {
      this.textViewSubtitle = textViewSubtitle;
    }
  }

}
