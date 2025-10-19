package com.newfeed.app.presentation.createpost;

import com.newfeed.app.domain.usecase.CreatePostUseCase;
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
public final class CreatePostViewModel_Factory implements Factory<CreatePostViewModel> {
  private final Provider<CreatePostUseCase> createPostUseCaseProvider;

  private final Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider;

  public CreatePostViewModel_Factory(Provider<CreatePostUseCase> createPostUseCaseProvider,
      Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider) {
    this.createPostUseCaseProvider = createPostUseCaseProvider;
    this.getCurrentUserUseCaseProvider = getCurrentUserUseCaseProvider;
  }

  @Override
  public CreatePostViewModel get() {
    return newInstance(createPostUseCaseProvider.get(), getCurrentUserUseCaseProvider.get());
  }

  public static CreatePostViewModel_Factory create(
      javax.inject.Provider<CreatePostUseCase> createPostUseCaseProvider,
      javax.inject.Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider) {
    return new CreatePostViewModel_Factory(Providers.asDaggerProvider(createPostUseCaseProvider), Providers.asDaggerProvider(getCurrentUserUseCaseProvider));
  }

  public static CreatePostViewModel_Factory create(
      Provider<CreatePostUseCase> createPostUseCaseProvider,
      Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider) {
    return new CreatePostViewModel_Factory(createPostUseCaseProvider, getCurrentUserUseCaseProvider);
  }

  public static CreatePostViewModel newInstance(CreatePostUseCase createPostUseCase,
      GetCurrentUserUseCase getCurrentUserUseCase) {
    return new CreatePostViewModel(createPostUseCase, getCurrentUserUseCase);
  }
}
