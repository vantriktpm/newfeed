package com.newfeed.app.presentation.newsfeed;

import com.newfeed.app.domain.usecase.GetAllPostsUseCase;
import com.newfeed.app.domain.usecase.GetCurrentUserUseCase;
import com.newfeed.app.domain.usecase.ToggleLikeUseCase;
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
public final class NewsFeedViewModel_Factory implements Factory<NewsFeedViewModel> {
  private final Provider<GetAllPostsUseCase> getAllPostsUseCaseProvider;

  private final Provider<ToggleLikeUseCase> toggleLikeUseCaseProvider;

  private final Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider;

  public NewsFeedViewModel_Factory(Provider<GetAllPostsUseCase> getAllPostsUseCaseProvider,
      Provider<ToggleLikeUseCase> toggleLikeUseCaseProvider,
      Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider) {
    this.getAllPostsUseCaseProvider = getAllPostsUseCaseProvider;
    this.toggleLikeUseCaseProvider = toggleLikeUseCaseProvider;
    this.getCurrentUserUseCaseProvider = getCurrentUserUseCaseProvider;
  }

  @Override
  public NewsFeedViewModel get() {
    return newInstance(getAllPostsUseCaseProvider.get(), toggleLikeUseCaseProvider.get(), getCurrentUserUseCaseProvider.get());
  }

  public static NewsFeedViewModel_Factory create(
      javax.inject.Provider<GetAllPostsUseCase> getAllPostsUseCaseProvider,
      javax.inject.Provider<ToggleLikeUseCase> toggleLikeUseCaseProvider,
      javax.inject.Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider) {
    return new NewsFeedViewModel_Factory(Providers.asDaggerProvider(getAllPostsUseCaseProvider), Providers.asDaggerProvider(toggleLikeUseCaseProvider), Providers.asDaggerProvider(getCurrentUserUseCaseProvider));
  }

  public static NewsFeedViewModel_Factory create(
      Provider<GetAllPostsUseCase> getAllPostsUseCaseProvider,
      Provider<ToggleLikeUseCase> toggleLikeUseCaseProvider,
      Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider) {
    return new NewsFeedViewModel_Factory(getAllPostsUseCaseProvider, toggleLikeUseCaseProvider, getCurrentUserUseCaseProvider);
  }

  public static NewsFeedViewModel newInstance(GetAllPostsUseCase getAllPostsUseCase,
      ToggleLikeUseCase toggleLikeUseCase, GetCurrentUserUseCase getCurrentUserUseCase) {
    return new NewsFeedViewModel(getAllPostsUseCase, toggleLikeUseCase, getCurrentUserUseCase);
  }
}
