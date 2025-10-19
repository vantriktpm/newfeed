package com.newfeed.app.di;

import com.newfeed.app.data.local.NewsFeedDatabase;
import com.newfeed.app.data.local.dao.UserDao;
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
public final class DatabaseModule_ProvideUserDaoFactory implements Factory<UserDao> {
  private final Provider<NewsFeedDatabase> databaseProvider;

  public DatabaseModule_ProvideUserDaoFactory(Provider<NewsFeedDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public UserDao get() {
    return provideUserDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideUserDaoFactory create(
      javax.inject.Provider<NewsFeedDatabase> databaseProvider) {
    return new DatabaseModule_ProvideUserDaoFactory(Providers.asDaggerProvider(databaseProvider));
  }

  public static DatabaseModule_ProvideUserDaoFactory create(
      Provider<NewsFeedDatabase> databaseProvider) {
    return new DatabaseModule_ProvideUserDaoFactory(databaseProvider);
  }

  public static UserDao provideUserDao(NewsFeedDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideUserDao(database));
  }
}
