package com.newfeed.app.di;

import com.newfeed.app.data.local.NewsFeedDatabase;
import com.newfeed.app.data.local.dao.LikeDao;
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
public final class DatabaseModule_ProvideLikeDaoFactory implements Factory<LikeDao> {
  private final Provider<NewsFeedDatabase> databaseProvider;

  public DatabaseModule_ProvideLikeDaoFactory(Provider<NewsFeedDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public LikeDao get() {
    return provideLikeDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideLikeDaoFactory create(
      javax.inject.Provider<NewsFeedDatabase> databaseProvider) {
    return new DatabaseModule_ProvideLikeDaoFactory(Providers.asDaggerProvider(databaseProvider));
  }

  public static DatabaseModule_ProvideLikeDaoFactory create(
      Provider<NewsFeedDatabase> databaseProvider) {
    return new DatabaseModule_ProvideLikeDaoFactory(databaseProvider);
  }

  public static LikeDao provideLikeDao(NewsFeedDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideLikeDao(database));
  }
}
