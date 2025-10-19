package com.newfeed.app.di;

import com.newfeed.app.data.local.NewsFeedDatabase;
import com.newfeed.app.data.local.dao.CommentDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideCommentDaoFactory implements Factory<CommentDao> {
  private final Provider<NewsFeedDatabase> databaseProvider;

  public DatabaseModule_ProvideCommentDaoFactory(Provider<NewsFeedDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public CommentDao get() {
    return provideCommentDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideCommentDaoFactory create(
      javax.inject.Provider<NewsFeedDatabase> databaseProvider) {
    return new DatabaseModule_ProvideCommentDaoFactory(Providers.asDaggerProvider(databaseProvider));
  }

  public static DatabaseModule_ProvideCommentDaoFactory create(
      Provider<NewsFeedDatabase> databaseProvider) {
    return new DatabaseModule_ProvideCommentDaoFactory(databaseProvider);
  }

  public static CommentDao provideCommentDao(NewsFeedDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideCommentDao(database));
  }
}
