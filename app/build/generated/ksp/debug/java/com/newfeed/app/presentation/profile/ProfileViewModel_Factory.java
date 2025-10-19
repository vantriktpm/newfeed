package com.newfeed.app.presentation.profile;

import com.newfeed.app.domain.usecase.GetAllPostsUseCase;
import com.newfeed.app.domain.usecase.GetCurrentUserUseCase;
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
public final class ProfileViewModel_Factory implements Factory<ProfileViewModel> {
  private final Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider;

  private final Provider<GetAllPostsUseCase> getAllPostsUseCaseProvider;

  public ProfileViewModel_Factory(Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider,
      Provider<GetAllPostsUseCase> getAllPostsUseCaseProvider) {
    this.getCurrentUserUseCaseProvider = getCurrentUserUseCaseProvider;
    this.getAllPostsUseCaseProvider = getAllPostsUseCaseProvider;
  }

  @Override
  public ProfileViewModel get() {
    return newInstance(getCurrentUserUseCaseProvider.get(), getAllPostsUseCaseProvider.get());
  }

  public static ProfileViewModel_Factory create(
      javax.inject.Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider,
      javax.inject.Provider<GetAllPostsUseCase> getAllPostsUseCaseProvider) {
    return new ProfileViewModel_Factory(Providers.asDaggerProvider(getCurrentUserUseCaseProvider), Providers.asDaggerProvider(getAllPostsUseCaseProvider));
  }

  public static ProfileViewModel_Factory create(
      Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider,
      Provider<GetAllPostsUseCase> getAllPostsUseCaseProvider) {
    return new ProfileViewModel_Factory(getCurrentUserUseCaseProvider, getAllPostsUseCaseProvider);
  }

  public static ProfileViewModel newInstance(GetCurrentUserUseCase getCurrentUserUseCase,
      GetAllPostsUseCase getAllPostsUseCase) {
    return new ProfileViewModel(getCurrentUserUseCase, getAllPostsUseCase);
  }
}
