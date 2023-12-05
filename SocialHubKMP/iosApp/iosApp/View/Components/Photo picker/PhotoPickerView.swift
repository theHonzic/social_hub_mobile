//
//  PhotoPickerView.swift
//  iosApp
//
//  Created by Jan Janovec on 28.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct PhotoPickerView: View {
    @State var selectState: ImageSelectionState = .empty
    
    @ViewBuilder private var content: some View {
        switch selectState {
        case .empty:
            VStack(spacing: 20) {
                Image(systemName: "pencil")
                    .font(.largeTitle)
                Text("Add photo")
            }
        case .selected(let image):
            image
                .resizable()
                .scaledToFill()
        case .loading:
            ProgressView()
                .scaleEffect(2)
                .tint(.red)
        }
    }
    
    var body: some View {
        content
            .frame(width: 250, height: 250)
            .background(Color.accentColor)
            .clipShape(Circle())
            .overlay(
                Circle()
                    .inset(by: -0.5)
                    .stroke(.gray, lineWidth: 3)
            )
            .transition(.opacity)
            .onTapGesture {
                withAnimation {
                    self.selectState =  [
                        ImageSelectionState.loading,
                        .empty,
                        .selected(image: .init("try"))
                    ]
                        .randomElement()!
                }
            }
    }
}

#Preview {
    VStack {
        PhotoPickerView(selectState: .empty)
        PhotoPickerView(selectState: .loading)
        PhotoPickerView(selectState: .selected(image: .init("try")))
    }
}

extension PhotoPickerView {
    enum ImageSelectionState {
        case empty
        case selected(image: Image)
        case loading
    }
}
