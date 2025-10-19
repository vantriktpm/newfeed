package com.newfeed.app.data.repository;

import com.newfeed.app.data.local.dao.LikeDao;
import com.newfeed.app.data.local.dao.PostDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class PostRepositoryImpl_Factory implements Factory<PostRepositoryImpl> {
  private final Provider<PostDao> postDaoProvider;

  private final Provider<LikeDao> likeDaoProvider;

  public PostRepositoryImpl_Factory(Provider<PostDao> postDaoProvider,
      Provider<LikeDao> likeDaoProvider) {
    this.postDaoProvider = postDaoProvider;
    this.likeDaoProvider = likeDaoProvider;
  }

  @Override
  public PostRepositoryImpl get() {
    return newInstance(postDaoProvider.get(), likeDaoProvider.get());
  }

  public static PostRepositoryImpl_Factory create(javax.inject.Provider<PostDao> postDaoProvider,
      javax.inject.Provider<LikeDao> likeDaoProvider) {
    return new PostRepositoryImpl_Factory(Providers.asDaggerProvider(postDaoProvider), Providers.asDaggerProvider(likeDaoProvider));
  }

  public static PostRepositoryImpl_Factory create(Provider<PostDao> postDaoProvider,
      Provider<LikeDao> likeDaoProvider) {
    return new PostRepositoryImpl_Factory(postDaoProvider, likeDaoProvider);
  }

  public static PostRepositoryImpl newInstance(PostDao postDao, LikeDao likeDao) {
    return new PostRepositoryImpl(postDao, likeDao);
  }
}
