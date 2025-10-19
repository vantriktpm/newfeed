package com.newfeed.app.di;

import android.content.Context;
import com.newfeed.app.data.local.NewsFeedDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DatabaseModule_ProvideNewfeedDatabaseFactory implements Factory<NewsFeedDatabase> {
  private final Provider<Context> contextProvider;

  public DatabaseModule_ProvideNewfeedDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public NewsFeedDatabase get() {
    return provideNewfeedDatabase(contextProvider.get());
  }

  public static DatabaseModule_ProvideNewfeedDatabaseFactory create(
      javax.inject.Provider<Context> contextProvider) {
    return new DatabaseModule_ProvideNewfeedDatabaseFactory(Providers.asDaggerProvider(contextProvider));
  }

  public static DatabaseModule_ProvideNewfeedDatabaseFactory create(
      Provider<Context> contextProvider) {
    return new DatabaseModule_ProvideNewfeedDatabaseFactory(contextProvider);
  }

  public static NewsFeedDatabase provideNewfeedDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideNewfeedDatabase(context));
  }
}
