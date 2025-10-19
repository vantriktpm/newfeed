package com.newfeed.app.presentation.comments;

import androidx.lifecycle.SavedStateHandle;
import com.newfeed.app.domain.usecase.CreateCommentUseCase;
import com.newfeed.app.domain.usecase.GetCommentsUseCase;
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
public final class CommentsViewModel_Factory implements Factory<CommentsViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<GetCommentsUseCase> getCommentsUseCaseProvider;

  private final Provider<CreateCommentUseCase> createCommentUseCaseProvider;

  private final Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider;

  public CommentsViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetCommentsUseCase> getCommentsUseCaseProvider,
      Provider<CreateCommentUseCase> createCommentUseCaseProvider,
      Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.getCommentsUseCaseProvider = getCommentsUseCaseProvider;
    this.createCommentUseCaseProvider = createCommentUseCaseProvider;
    this.getCurrentUserUseCaseProvider = getCurrentUserUseCaseProvider;
  }

  @Override
  public CommentsViewModel get() {
    return newInstance(savedStateHandleProvider.get(), getCommentsUseCaseProvider.get(), createCommentUseCaseProvider.get(), getCurrentUserUseCaseProvider.get());
  }

  public static CommentsViewModel_Factory create(
      javax.inject.Provider<SavedStateHandle> savedStateHandleProvider,
      javax.inject.Provider<GetCommentsUseCase> getCommentsUseCaseProvider,
      javax.inject.Provider<CreateCommentUseCase> createCommentUseCaseProvider,
      javax.inject.Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider) {
    return new CommentsViewModel_Factory(Providers.asDaggerProvider(savedStateHandleProvider), Providers.asDaggerProvider(getCommentsUseCaseProvider), Providers.asDaggerProvider(createCommentUseCaseProvider), Providers.asDaggerProvider(getCurrentUserUseCaseProvider));
  }

  public static CommentsViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetCommentsUseCase> getCommentsUseCaseProvider,
      Provider<CreateCommentUseCase> createCommentUseCaseProvider,
      Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider) {
    return new CommentsViewModel_Factory(savedStateHandleProvider, getCommentsUseCaseProvider, createCommentUseCaseProvider, getCurrentUserUseCaseProvider);
  }

  public static CommentsViewModel newInstance(SavedStateHandle savedStateHandle,
      GetCommentsUseCase getCommentsUseCase, CreateCommentUseCase createCommentUseCase,
      GetCurrentUserUseCase getCurrentUserUseCase) {
    return new CommentsViewModel(savedStateHandle, getCommentsUseCase, createCommentUseCase, getCurrentUserUseCase);
  }
}
