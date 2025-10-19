package com.newfeed.app;

import com.newfeed.app.data.local.DatabaseInitializer;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import kotlinx.coroutines.CoroutineScope;

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
public final class NewsfeedApplication_MembersInjector implements MembersInjector<NewsfeedApplication> {
  private final Provider<DatabaseInitializer> databaseInitializerProvider;

  private final Provider<CoroutineScope> applicationScopeProvider;

  public NewsfeedApplication_MembersInjector(
      Provider<DatabaseInitializer> databaseInitializerProvider,
      Provider<CoroutineScope> applicationScopeProvider) {
    this.databaseInitializerProvider = databaseInitializerProvider;
    this.applicationScopeProvider = applicationScopeProvider;
  }

  public static MembersInjector<NewsfeedApplication> create(
      Provider<DatabaseInitializer> databaseInitializerProvider,
      Provider<CoroutineScope> applicationScopeProvider) {
    return new NewsfeedApplication_MembersInjector(databaseInitializerProvider, applicationScopeProvider);
  }

  public static MembersInjector<NewsfeedApplication> create(
      javax.inject.Provider<DatabaseInitializer> databaseInitializerProvider,
      javax.inject.Provider<CoroutineScope> applicationScopeProvider) {
    return new NewsfeedApplication_MembersInjector(Providers.asDaggerProvider(databaseInitializerProvider), Providers.asDaggerProvider(applicationScopeProvider));
  }

  @Override
  public void injectMembers(NewsfeedApplication instance) {
    injectDatabaseInitializer(instance, databaseInitializerProvider.get());
    injectApplicationScope(instance, applicationScopeProvider.get());
  }

  @InjectedFieldSignature("com.newfeed.app.NewsfeedApplication.databaseInitializer")
  public static void injectDatabaseInitializer(NewsfeedApplication instance,
      DatabaseInitializer databaseInitializer) {
    instance.databaseInitializer = databaseInitializer;
  }

  @InjectedFieldSignature("com.newfeed.app.NewsfeedApplication.applicationScope")
  public static void injectApplicationScope(NewsfeedApplication instance,
      CoroutineScope applicationScope) {
    instance.applicationScope = applicationScope;
  }
}
