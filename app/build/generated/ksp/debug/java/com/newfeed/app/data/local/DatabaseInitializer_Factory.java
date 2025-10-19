package com.newfeed.app.data.local;

import com.newfeed.app.data.local.dao.PostDao;
import com.newfeed.app.data.local.dao.UserDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DatabaseInitializer_Factory implements Factory<DatabaseInitializer> {
  private final Provider<UserDao> userDaoProvider;

  private final Provider<PostDao> postDaoProvider;

  public DatabaseInitializer_Factory(Provider<UserDao> userDaoProvider,
      Provider<PostDao> postDaoProvider) {
    this.userDaoProvider = userDaoProvider;
    this.postDaoProvider = postDaoProvider;
  }

  @Override
  public DatabaseInitializer get() {
    return newInstance(userDaoProvider.get(), postDaoProvider.get());
  }

  public static DatabaseInitializer_Factory create(javax.inject.Provider<UserDao> userDaoProvider,
      javax.inject.Provider<PostDao> postDaoProvider) {
    return new DatabaseInitializer_Factory(Providers.asDaggerProvider(userDaoProvider), Providers.asDaggerProvider(postDaoProvider));
  }

  public static DatabaseInitializer_Factory create(Provider<UserDao> userDaoProvider,
      Provider<PostDao> postDaoProvider) {
    return new DatabaseInitializer_Factory(userDaoProvider, postDaoProvider);
  }

  public static DatabaseInitializer newInstance(UserDao userDao, PostDao postDao) {
    return new DatabaseInitializer(userDao, postDao);
  }
}
