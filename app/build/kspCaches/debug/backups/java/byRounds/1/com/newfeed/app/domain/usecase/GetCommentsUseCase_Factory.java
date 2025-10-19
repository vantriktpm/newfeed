package com.newfeed.app.domain.usecase;

import com.newfeed.app.domain.repository.CommentRepository;
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
public final class GetCommentsUseCase_Factory implements Factory<GetCommentsUseCase> {
  private final Provider<CommentRepository> commentRepositoryProvider;

  public GetCommentsUseCase_Factory(Provider<CommentRepository> commentRepositoryProvider) {
    this.commentRepositoryProvider = commentRepositoryProvider;
  }

  @Override
  public GetCommentsUseCase get() {
    return newInstance(commentRepositoryProvider.get());
  }

  public static GetCommentsUseCase_Factory create(
      javax.inject.Provider<CommentRepository> commentRepositoryProvider) {
    return new GetCommentsUseCase_Factory(Providers.asDaggerProvider(commentRepositoryProvider));
  }

  public static GetCommentsUseCase_Factory create(
      Provider<CommentRepository> commentRepositoryProvider) {
    return new GetCommentsUseCase_Factory(commentRepositoryProvider);
  }

  public static GetCommentsUseCase newInstance(CommentRepository commentRepository) {
    return new GetCommentsUseCase(commentRepository);
  }
}
