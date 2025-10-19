package com.newfeed.app.data.repository;

import com.newfeed.app.data.local.dao.CommentDao;
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
public final class CommentRepositoryImpl_Factory implements Factory<CommentRepositoryImpl> {
  private final Provider<CommentDao> commentDaoProvider;

  private final Provider<PostDao> postDaoProvider;

  public CommentRepositoryImpl_Factory(Provider<CommentDao> commentDaoProvider,
      Provider<PostDao> postDaoProvider) {
    this.commentDaoProvider = commentDaoProvider;
    this.postDaoProvider = postDaoProvider;
  }

  @Override
  public CommentRepositoryImpl get() {
    return newInstance(commentDaoProvider.get(), postDaoProvider.get());
  }

  public static CommentRepositoryImpl_Factory create(
      javax.inject.Provider<CommentDao> commentDaoProvider,
      javax.inject.Provider<PostDao> postDaoProvider) {
    return new CommentRepositoryImpl_Factory(Providers.asDaggerProvider(commentDaoProvider), Providers.asDaggerProvider(postDaoProvider));
  }

  public static CommentRepositoryImpl_Factory create(Provider<CommentDao> commentDaoProvider,
      Provider<PostDao> postDaoProvider) {
    return new CommentRepositoryImpl_Factory(commentDaoProvider, postDaoProvider);
  }

  public static CommentRepositoryImpl newInstance(CommentDao commentDao, PostDao postDao) {
    return new CommentRepositoryImpl(commentDao, postDao);
  }
}
