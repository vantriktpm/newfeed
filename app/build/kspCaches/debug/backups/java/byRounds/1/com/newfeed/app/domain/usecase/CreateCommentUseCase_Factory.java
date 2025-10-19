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
public final class CreateCommentUseCase_Factory implements Factory<CreateCommentUseCase> {
  private final Provider<CommentRepository> commentRepositoryProvider;

  public CreateCommentUseCase_Factory(Provider<CommentRepository> commentRepositoryProvider) {
    this.commentRepositoryProvider = commentRepositoryProvider;
  }

  @Override
  public CreateCommentUseCase get() {
    return newInstance(commentRepositoryProvider.get());
  }

  public static CreateCommentUseCase_Factory create(
      javax.inject.Provider<CommentRepository> commentRepositoryProvider) {
    return new CreateCommentUseCase_Factory(Providers.asDaggerProvider(commentRepositoryProvider));
  }

  public static CreateCommentUseCase_Factory create(
      Provider<CommentRepository> commentRepositoryProvider) {
    return new CreateCommentUseCase_Factory(commentRepositoryProvider);
  }

  public static CreateCommentUseCase newInstance(CommentRepository commentRepository) {
    return new CreateCommentUseCase(commentRepository);
  }
}
