//
//  SelectInputFIeld.swift
//  iosApp
//
//  Created by Jan Janovec on 27.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import SwiftUI

struct SelectInputFIeld<SelectableDelegate: Selectable>: View {
    @State var isSelectionSheetVisible = false
    @Binding var selectedItem: SelectableDelegate
    var list: [SelectableDelegate]
    var body: some View {
        HStack(alignment: .center, spacing: 8) {
            Text(selectedItem.listIcon)
            Text(selectedItem.listTitle)
            Spacer()
            Image(systemName: "chevron.down")
                .rotationEffect(.init(degrees: isSelectionSheetVisible ? 180 : 0))
        }
        .frame(maxWidth: .infinity)
        .padding(.vertical, 8)
        .padding(.horizontal, 14)
        .background(.ultraThinMaterial)
        .clipShape(RoundedRectangle(cornerRadius: 30))
        .onTapGesture {
            if !list.isEmpty {
                isSelectionSheetVisible = true
            }
        }
        .animation(.default, value: isSelectionSheetVisible)
        .sheet(isPresented: $isSelectionSheetVisible) {
            ScrollView {
                VStack {
                    ForEach(list, id: \.id) { item in
                        Text(item.listTitle)
                            .font(.title)
                            .foregroundStyle(Color.red)
                            .onTapGesture {
                                withAnimation {
                                    isSelectionSheetVisible = false
                                    selectedItem = item
                                }
                            }
                    }
                }
            }
            .presentationDetents([.medium, .large])
        }
    }
}
